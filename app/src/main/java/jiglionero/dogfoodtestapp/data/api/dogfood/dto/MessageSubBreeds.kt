package jiglionero.dogfoodtestapp.data.api.dogfood.dto

import jiglionero.dogfoodtestapp.data.entity.Breed

data class MessageSubBreeds(
    override val message: List<String>,
    override val status: MessageStatus
) : Message {
    fun getBreedList(parentName: String): List<Breed> {
        val list = mutableListOf<Breed>()
        message.forEach { subBreedName ->
            list.add(Breed(subBreedName, parentName))
        }
        return list
    }
}