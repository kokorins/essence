package io.github.cdimascio.essence.extractors

import org.jsoup.nodes.Document
import java.util.*

internal object LanguageExtractor {
    fun extract(doc: Document): String {
        var lang = doc.select("html").attr("lang")
        if (lang.isBlank()) {
            lang = doc.selectFirst("""
                meta[name=lang],
                meta[http-equiv=content-language]
            """.trimIndent())?.attr("content") ?: ""
        }
        if (lang.isNotBlank() && lang.length >= 2) {
            // return the first 2 letters ISO lang code with no country
            return lang.cleanse().substring(0, 2).lowercase(Locale.getDefault())
        }
        return ""
    }
}
