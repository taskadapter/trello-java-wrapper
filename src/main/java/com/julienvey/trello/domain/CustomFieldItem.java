package com.julienvey.trello.domain;

import java.util.Collections;
import java.util.Map;

public class CustomFieldItem extends TrelloEntity {

    private String id;
    private String cardId;
    private Map<String, String> value;
    private String idCustomField;
    private String idModel;
    private String ModelType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(FieldType fieldType, String value) {
        this.value = Collections.singletonMap(fieldType.name().toLowerCase(), value);
    }

    public String getIdCustomField() {
        return idCustomField;
    }

    public void setIdCustomField(String idCustomField) {
        this.idCustomField = idCustomField;
    }

    public CustomField getCustomField() {
        return trelloService.getCustomField(idCustomField);
    }

    public String getIdModel() {
        return idModel;
    }

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    public String getModelType() {
        return ModelType;
    }

    public void setModelType(String modelType) {
        ModelType = modelType;
    }

    public CustomFieldItem update() {
        return trelloService.updateCustomFieldItem(cardId, this);
    }

    public enum FieldType {

        NUMBER, DATE, TEXT, CHECKBOX, LIST

    }
}
