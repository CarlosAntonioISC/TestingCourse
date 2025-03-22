package com.android.testing.examples

/**
 * Un slug es una versión simplificada de un título o frase que se utiliza en URLs,
 * eliminando caracteres especiales y reemplazando espacios con guiones (-),
 * lo que facilita su uso en la web.
 *
 * Convierte el texto a minúsculas
 *
 * Elimina espacios extra y los reemplaza con -
 *
 * Elimina caracteres especiales que no son letras o números
 *
 */
class SlugGenerator {

    fun generateSlug(text: String): String {
        return text.lowercase()
            .trim()
            .replace(Regex("\\s+"), "-")
            .replace(Regex("[^a-z0-9-]"), "")
    }

}