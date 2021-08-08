package io.github.cdimascio.essence.extractors

import org.jsoup.nodes.Document
import java.util.*

internal object FaviconExtractor {
    fun extract(doc: Document): String {
        val favicon = doc.select("link").filter {
            it.attr("rel").lowercase(Locale.getDefault()) == "shortcut icon"
        }
        if (favicon.isNotEmpty()) {
            return favicon[0].attr("href")
        }
        return ""
    }
}
