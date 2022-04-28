package form.patterns.builder;

import java.util.Date;

public class Store {
    private final String name;
    private final Date createdDate;
    private final Integer employeesQuantity;

    public Store(String name, Date createdDate, Integer employeesQuantity) {
        this.name = name;
        this.createdDate = createdDate;
        this.employeesQuantity = employeesQuantity;
    }

    @Override
    public String toString() {
        return "Store{" +
               "name='" + name + '\'' +
               ", createdDate=" + createdDate +
               ", employeesQuantity=" + employeesQuantity +
               '}';
    }

    public static class StoreBuilder {
        private String name;
        private Date createdDate;
        private Integer employeesQuantity;

        public StoreBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StoreBuilder setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public StoreBuilder setEmployeesQuantity(Integer employeesQuantity) {
            this.employeesQuantity = employeesQuantity;
            return this;
        }

        public Store build() {
            return new Store(this.name, this.createdDate, this.employeesQuantity);
        }
    }

    public static void main(String[] args) {
        final StoreBuilder storeBuilder = new StoreBuilder();
        storeBuilder.setCreatedDate(new Date()).setName("dsds");
        final Store build = storeBuilder.build();
        System.out.println(build);
    }
}
