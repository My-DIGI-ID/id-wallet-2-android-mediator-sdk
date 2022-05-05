package com.digital_enabling.android_mediator_sdk.discoveryController

import com.digital_enabling.android_aries_sdk.common.AriesFrameworkException
import com.digital_enabling.android_aries_sdk.common.ErrorCode
import com.digital_enabling.android_aries_sdk.configuration.IProvisioningService
import com.digital_enabling.android_aries_sdk.configuration.ProvisioningRecord
import com.digital_enabling.android_aries_sdk.messagedispatcher.DefaultMessageService
import com.digital_enabling.android_aries_sdk.wallet.IWalletRecordService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import org.hyperledger.indy.sdk.wallet.Wallet
import java.net.URI

class DiscoveryController(
    private val provisioningService: IProvisioningService,
    private val walletRecordService: IWalletRecordService
) : IDiscoveryController {


    override suspend fun discoverConfigurationAsync(
        mediatorEndpoint: URI
    ): AgentPublicConfiguration {
        var responseBody: String? = null
        try {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("${mediatorEndpoint}/.well-known/agent-configuration")
                .get()
                .addHeader("Content-Type", DefaultMessageService.AgentWireMessageMimeType)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful)
                    throw AriesFrameworkException(
                        ErrorCode.A2A_MESSAGE_TRANSMISSION_ERROR,
                        "Dispatch Failure. Endpoint:${mediatorEndpoint}/.well-known/agent-configuration Content: ${response.body}"
                    )
                responseBody = response.body?.string()
            }
        } catch (e: Exception) {
            throw Exception("Failed to send HTTP Request.", e)
        }

        try {
            return Json.decodeFromString(responseBody!!)
        } catch (e: Exception) {
            throw Exception("Could not deserialize HttpResponse", e)
        }
    }

    override suspend fun updateProvisioningRecordAsync(
        wallet: Wallet,
        discovery: AgentPublicConfiguration
    ): ProvisioningRecord {
        val provisioningRecord: ProvisioningRecord
        try {
            provisioningRecord = provisioningService.getProvisioning(wallet)
        } catch (e: AriesFrameworkException) {
            throw Exception("No ProvisioningRecord found.", e)
        }

        provisioningRecord.endpoint.uri = discovery.serviceEndpoint
        provisioningRecord.endpoint.verkey = arrayOf(discovery.routingKey)

        try {
            walletRecordService.update(wallet, provisioningRecord)
        } catch (e: AriesFrameworkException) {
            throw Exception("Could not update record", e)
        }

        return provisioningRecord
    }
}