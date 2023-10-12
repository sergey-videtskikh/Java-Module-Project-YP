public record Item(String name, double cost) {

    @Override
    public String toString() {
        return String.format("Товар: %s, стоимость: %.2f", name, cost);
    }
}
