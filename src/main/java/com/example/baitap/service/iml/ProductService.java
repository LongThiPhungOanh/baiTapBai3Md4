package com.example.baitap.service.iml;
import com.example.baitap.model.Product;
import com.example.baitap.service.IGenericProduct;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IGenericProduct {
    List<Product> products;
    public ProductService(){
        products = new ArrayList<>();
        products.add(new Product(1, "Laptop Dell XPS 13", 1299.99, "Laptop siêu mỏng và nhẹ, màn hình InfinityEdge", "Dell"));
        products.add(new Product(2, "Điện thoại iPhone 12 Pro", 1099.99, "Chip A14 Bionic, hệ thống camera Pro, OLED Super Retina XDR", "Apple"));
        products.add(new Product(3, "Máy ảnh Canon EOS R5", 3799.99, "Máy ảnh full-frame, quay video 8K, Dual Pixel CMOS AF II", "Canon"));
        products.add(new Product(4, "Giày thể thao Nike Air Max 270", 129.99, "Giày thể thao thoáng khí, đệm Air Max lớn nhất từng có", "Nike"));
        products.add(new Product(5, "TV Samsung QLED Q90T 4K", 1499.99, "Màn hình QLED 4K, Quantum Processor 4K, Object Tracking Sound+", "Samsung"));

    }
    @Override
    public List<Product> finAll() {
        List<Product> productList = products;
        List<Product> returnList = new ArrayList<>();
        for (Product p: productList) {
            if (!p.getStatus().equals("N")){
                returnList.add(p);
            }
        }
        return returnList;
    }

    @Override
    public Product finOne(int id) {
        Product product = new Product();
        for (Product p: products) {
            if (p.getId() == id){
                product = p;
            }
        }
        return product;
    }

    @Override
    public void update(Product product) {
        Product product1 = finOne(product.getId());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setDescribe(product.getDescribe());
        product1.setProducer(product.getProducer());
    }

    @Override
    public void create(Product product) {
        products.add(product);
    }

    @Override
    public void remove(int id) {
        Product product = finOne(id);
        product.setStatus("N");
    }
    public List<Product> searchByName(String name){
        List<Product> productList = finAll();
        List<Product> products1 = new ArrayList<>();
        for (Product p : productList) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                products1.add(p);
            }
        }
        return products1;
    }
}
