/**
 * OpenClaw Source Reference:
 * - ../openclaw/src/agents/tools/browser/(all)
 *
 * AndroidOpenClaw adaptation: browser tool client.
 */
package info.plateaukao.einkbro.browser

interface DomainInterface {
    fun getDomains(): List<String>
    fun addDomain(domain: String)
    fun deleteDomain(domain: String)
    fun deleteAllDomains()
}
