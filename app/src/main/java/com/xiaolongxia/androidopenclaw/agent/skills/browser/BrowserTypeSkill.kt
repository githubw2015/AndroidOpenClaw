package com.xiaolongxia.androidopenclaw.agent.skills.browser

/**
 * OpenClaw Source Reference:
 * - ../openclaw/src/browser/client-actions-core.ts
 */


import android.content.Context
import com.xiaolongxia.androidopenclaw.agent.tools.Skill
import com.xiaolongxia.androidopenclaw.agent.tools.SkillResult
import com.xiaolongxia.androidopenclaw.providers.FunctionDefinition
import com.xiaolongxia.androidopenclaw.providers.ParametersSchema
import com.xiaolongxia.androidopenclaw.providers.PropertySchema
import com.xiaolongxia.androidopenclaw.providers.ToolDefinition
import com.xiaolongxia.androidopenclaw.browser.BrowserToolClient

/**
 * browser_type - Type text
 */
class BrowserTypeSkill(private val context: Context) : Skill {
    override val name = "browser_type"
    override val description = "Type text into an input field in the browser"

    override fun getToolDefinition(): ToolDefinition {
        return ToolDefinition(
            type = "function",
            function = FunctionDefinition(
                name = name,
                description = "Type text into an input field in the browser. Provide 'selector' (CSS selector), 'text' (text to type), optional 'clear' (clear before typing, default: true), optional 'submit' (submit form after typing, default: false). Example: {\"selector\": \"input[name='q']\", \"text\": \"hello world\", \"clear\": true}",
                parameters = ParametersSchema(
                    type = "object",
                    properties = mapOf(
                        "selector" to PropertySchema(
                            "string",
                            "CSS selector for the input element"
                        ),
                        "text" to PropertySchema(
                            "string",
                            "The text to type"
                        ),
                        "clear" to PropertySchema(
                            "boolean",
                            "Whether to clear before typing (default: true)"
                        ),
                        "submit" to PropertySchema(
                            "boolean",
                            "Whether to submit form after typing (default: false)"
                        )
                    ),
                    required = listOf("selector", "text")
                )
            )
        )
    }

    override suspend fun execute(args: Map<String, Any?>): SkillResult {
        val selector = args["selector"] as? String
            ?: return SkillResult.error("Missing required parameter: selector")

        val text = args["text"] as? String
            ?: return SkillResult.error("Missing required parameter: text")

        val clear = args["clear"] as? Boolean ?: true
        val submit = args["submit"] as? Boolean ?: false

        return try {
            val browserClient = BrowserToolClient(context)
            val toolArgs = mapOf(
                "selector" to selector,
                "text" to text,
                "clear" to clear,
                "submit" to submit
            )
            val result = browserClient.executeToolAsync("browser_type", toolArgs)

            if (result.success) {
                SkillResult.success(
                    "Successfully typed text into $selector",
                    result.data ?: emptyMap()
                )
            } else {
                SkillResult.error(result.error ?: "Type failed")
            }
        } catch (e: Exception) {
            SkillResult.error("Failed to type: ${e.message}")
        }
    }
}
