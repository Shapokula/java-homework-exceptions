import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    Product product1 = new Product(1, "Хлеб", 20);
    Product product2 = new Product(2, "Макароны", 50);
    Product product3 = new Product(3, "Колбаса", 100);

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.removeById(2);
        Product[] expected = { product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundException() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(4);
        });
    }

    @Test
    public void shouldAddNewProduct() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product[] expected = { product1, product2, product3 };
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddNewProductIfIdAlreadyExists() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product product4 = new Product(1, "Рубашка", 500);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product4);
        });
    }

}