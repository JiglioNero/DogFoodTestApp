package jiglionero.dogfoodtestapp.data.api.dogfood.dto

data class MessageImages (
    override val message: List<String>,
    override val status: MessageStatus
) : Message