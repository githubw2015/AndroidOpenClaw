package com.xiaolongxia.androidopenclaw.agent.tools

/**
 * OpenClaw Source Reference:
 * - 无 OpenClaw 对应 (Android 平台独有)
 */


import com.xiaolongxia.androidopenclaw.gateway.methods.ConfigMethods
import com.xiaolongxia.androidopenclaw.providers.FunctionDefinition
import com.xiaolongxia.androidopenclaw.providers.ParametersSchema
import com.xiaolongxia.androidopenclaw.providers.PropertySchema
import com.xiaolongxia.androidopenclaw.providers.ToolDefinition

/**
 * Read value from /sdcard/AndroidOpenClaw/openclaw.json by path.
 */
class ConfigGetTool(
    private val configMethods: ConfigMethods
) : Tool {
    override val name = "config_get"
    override val description = "Read a configuration value from openclaw.json by dot path"

    override fun getToolDefinition(): ToolDefinition {
        return ToolDefinition(
            type = "function",
            function = FunctionDefinition(
                name = name,
                description = description,
                parameters = ParametersSchema(
                    type = "object",
                    properties = mapOf(
                        "path" to PropertySchema("string", "Dot path, e.g. channels.feishu.appId")
                    ),
                    required = listOf("path")
                )
            )
        )
    }

    override suspend fun execute(args: Map<String, Any?>): ToolResult {
        val path = args["path"] as? String
            ?: return ToolResult.error("Missing required parameter: path")

        val result = configMethods.configGet(mapOf("path" to path))
        return if (result.success) {
            ToolResult.success(result.config?.toString() ?: "null")
        } else {
            ToolResult.error(result.error ?: "Failed to read config")
        }
    }
}
