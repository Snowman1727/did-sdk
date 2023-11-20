package com.teleinfo.didsdk.util;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.diff.JsonDiff;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.SpecVersion.VersionFlag;

import java.util.Set;

public class JsonPatchUtils {

    /**
     * 根据source和diff得到最新json
     * @param source jsonObject
     * @param patch [{"op":"move","from":"/a","path":"/b/2"}]
     * @return jsonObject
     * @throws Exception
     */
    public static String applyPatch(String source, String patch) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode sourceNode = objectMapper.readTree(source);
        JsonNode patchNode = JsonLoader.fromString(patch);
        JsonPatch jsonPatch = JsonPatch.fromJson(patchNode);
        JsonNode resultNode = jsonPatch.apply(sourceNode);
        String resultString = objectMapper.writeValueAsString(resultNode);
        return resultString;
    }

    /**
     * 比较两个json串的差异
     * @param source
     * @param target
     * @return [{"op":"move","from":"/a","path":"/b/2"}]
     * @throws Exception
     */
    public static String applyDiff(String source, String target) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode sourceNode = objectMapper.readTree(source);
        JsonNode patchNode = objectMapper.readTree(target);
        JsonNode patch = JsonDiff.asJson(sourceNode,patchNode);
        return patch.toPrettyString();
    }

    public static boolean isValid(String patch) throws Exception {
        String schemaString = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"definitions\":{\"path\":{\"description\":\"AJSONPointerpath.\",\"type\":\"string\"}},\"items\":{\"oneOf\":[{\"additionalProperties\":false,\"required\":[\"value\",\"op\",\"path\"],\"properties\":{\"path\":{\"$ref\":\"#/definitions/path\"},\"op\":{\"description\":\"Theoperationtoperform.\",\"type\":\"string\",\"enum\":[\"add\",\"replace\",\"test\"]},\"value\":{\"description\":\"Thevaluetoadd,replaceortest.\"}}},{\"additionalProperties\":false,\"required\":[\"op\",\"path\"],\"properties\":{\"path\":{\"$ref\":\"#/definitions/path\"},\"op\":{\"description\":\"Theoperationtoperform.\",\"type\":\"string\",\"enum\":[\"remove\"]}}},{\"additionalProperties\":false,\"required\":[\"from\",\"op\",\"path\"],\"properties\":{\"path\":{\"$ref\":\"#/definitions/path\"},\"op\":{\"description\":\"Theoperationtoperform.\",\"type\":\"string\",\"enum\":[\"move\",\"copy\"]},\"from\":{\"$ref\":\"#/definitions/path\",\"description\":\"AJSONPointerpathpointingtothelocationtomove/copyfrom.\"}}}]},\"title\":\"JSONschemaforJSONPatchfiles\",\"type\":\"array\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patchNode = objectMapper.readTree(patch);
        JsonNode schemaNode = objectMapper.readTree(schemaString);
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.builder(JsonSchemaFactory.getInstance(VersionFlag.V4))
                .objectMapper(objectMapper).build();
        JsonSchema schema = schemaFactory.getSchema(schemaNode);
        Set<ValidationMessage> errors = schema.validate(patchNode);
        return errors.isEmpty();
    }

    private static int crc = 0xFFFF;

    public static int getValue() {
        return crc;
    }

    public static void update(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            crc = (crc >>> 8) | (crc << 8);
            crc ^= (data[i] & 0xFF);
            crc ^= ((crc & 0xFF) >> 4);
            crc ^= (crc << 12);
            crc ^= ((crc & 0xFF) << 5);
        }
    }
}
