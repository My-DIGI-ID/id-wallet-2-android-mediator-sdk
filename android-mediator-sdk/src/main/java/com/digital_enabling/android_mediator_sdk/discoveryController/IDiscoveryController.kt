package com.digital_enabling.android_mediator_sdk.discoveryController

import com.digital_enabling.android_aries_sdk.configuration.ProvisioningRecord
import org.hyperledger.indy.sdk.wallet.Wallet
import java.net.URI

interface IDiscoveryController {
    suspend fun discoverConfigurationAsync(mediatorEndpoint: URI): AgentPublicConfiguration

    suspend fun updateProvisioningRecordAsync(
        wallet: Wallet,
        discovery: AgentPublicConfiguration
    ): ProvisioningRecord
}