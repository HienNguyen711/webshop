package project.webshop.model.entity;

public enum ProductType {
    BAIXA("Baixa"), MEDIA("Média"), ALTA("Alta");



    private String productType;

    ProductType(String descricao) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }
}
