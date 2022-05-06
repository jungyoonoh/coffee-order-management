package com.example.gccoffeeserver.repository;

import com.example.gccoffeeserver.model.Category;
import com.example.gccoffeeserver.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.example.gccoffeeserver.util.JdbcUtils.*;

@Repository
public class ProductJdbcRepository implements ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final Integer SUCCESSFULLY_UPDATE_ONE_ROW = 1;

    public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from products where id = UUID_TO_BIN(:id)",
                    Map.of("id", id.toString().getBytes()), productRowMapper));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Empty object was returned while findById Method");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from products where name = :name",
                    Map.of("name", name), productRowMapper));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Empty object was returned while findByName Method");
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return jdbcTemplate.query("select * from products where category = :category",
                Map.of("category", category),
                productRowMapper);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products", productRowMapper);
    }

    @Override
    public Product insert(Product product) {
        Integer result = jdbcTemplate.update("INSERT INTO products(id, name, category, price, description, created_at, updated_at) " +
                "VALUES (UUID_TO_BIN(:id), :name, :category, :price, :description, :createdAt, :updatedAt)", toParamMap(product));
        if (!result.equals(SUCCESSFULLY_UPDATE_ONE_ROW)) {
            logger.error("Failed to create product");
            throw new RuntimeException("Nothing was inserted");
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        Integer update = jdbcTemplate.update("UPDATE products SET name = :name, category = :category, price = :price, description = :description WHERE id = UUID_TO_BIN(:id)",
                toParamMap(product));
        if (!update.equals(SUCCESSFULLY_UPDATE_ONE_ROW)) {
            logger.error("Failed to update product");
            throw new RuntimeException("Nothing was updated");
        }
        return product;
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM products", Map.of());
    }

    @Override
    public void deleteById(UUID id) {
        Integer delete = jdbcTemplate.update("DELETE FROM products WHERE id = UUID_TO_BIN(:id)",
                Map.of("id", id.toString().getBytes()));
        if (!delete.equals(SUCCESSFULLY_UPDATE_ONE_ROW)) {
            logger.error("Failed to delete product");
            throw new RuntimeException("Nothing was deleted");
        }
    }

    private static final RowMapper<Product> productRowMapper = (resultSet, i) -> {
        var productId = toUUID(resultSet.getBytes("id"));
        var productName = resultSet.getString("name");
        var category = Category.valueOf(resultSet.getString("category"));
        var price = resultSet.getLong("price");
        var description = resultSet.getString("description");
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Product(productId, productName, category, price, description, createdAt, updatedAt);
    };

    private Map<String, Object> toParamMap(Product product) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("id", product.getId().toString().getBytes());
        paramMap.put("name", product.getName());
        paramMap.put("category", product.getCategory().toString());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("createdAt", product.getCreatedAt());
        paramMap.put("updatedAt", product.getUpdatedAt());
        return paramMap;
    }
}
