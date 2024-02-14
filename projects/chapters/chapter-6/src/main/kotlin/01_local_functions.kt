import javafx.scene.text.Text

data class FormData(
    val name: String?,
    val role: String?,
    val address: String?
)

val nameTitle: Text = TODO()
val nameContent: Text = TODO()
val roleTitle: Text = TODO()
val roleContent: Text = TODO()
val addressTitle: Text = TODO()
val addressContent: Text = TODO()

private fun updateFormData(formData: FormData) {
    fun setTextAndVisibility(title: Text, content: Text, text: String?) {
        val shouldBeVisible = text.isNullOrBlank().not()
        title.isVisible = shouldBeVisible
        content.isVisible = shouldBeVisible
        content.text = text
    }

    setTextAndVisibility(nameTitle, nameContent, formData.name)
    setTextAndVisibility(roleTitle, roleContent, formData.role)
    setTextAndVisibility(addressTitle, addressContent, formData.address)
}
