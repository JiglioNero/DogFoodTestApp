package jiglionero.dogfoodtestapp.data.api.dogfood.dto

import jiglionero.dogfoodtestapp.data.entity.Breed

data class MessageAllBreads(
    override val message: Map<String, List<String>>,
    override val status: MessageStatus
) : Message {
    fun getBreedList(): List<Breed> {
        val list = mutableListOf<Breed>()
        message.forEach { (parentName, subBreeds) ->
            if (subBreeds.isEmpty()) {
                list.add(Breed(parentName))
            } else {
                subBreeds.forEach { subBreedName ->
                    list.add(Breed(subBreedName, parentName))
                }
            }
        }
        return list
    }
}