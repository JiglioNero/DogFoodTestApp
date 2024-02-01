package jiglionero.dogfoodtestapp.data.api.dogfood.dto

import com.google.gson.annotations.SerializedName

enum class MessageStatus {
    @SerializedName("success")
    SUCCESS,

    @SerializedName("failure")
    FAILURE
}