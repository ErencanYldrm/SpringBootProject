package kodlama.northwind.business.concretes;

import kodlama.northwind.business.abstracts.ProductService;
import kodlama.northwind.core.utilities.results.DataResult;
import kodlama.northwind.core.utilities.results.Result;
import kodlama.northwind.core.utilities.results.SuccessResult;
import kodlama.northwind.dataAccess.abstracts.ProductDao;
import kodlama.northwind.entities.concretes.Product;
import kodlama.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        super();
        this.productDao = productDao;
    }

    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessResult<List<Product>>
        (this.productDao.findAll(),"data listelendi");


    }

    @Override
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return new SuccessResult<List<Product>>(this.productDao.findAll(pageable).getContent());
    }

    public DataResult<List<Product>> getAllSorted(){
        Sort sort = Sort.by(Sort.Direction.ASC,"productName");
        return new SuccessResult<List<Product>>(this.productDao.findAll(sort));
    }

    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Ürün eklendi");
    }

    public DataResult<Product> getByProductName(String productName) {
        return new SuccessResult<Product>
                (this.productDao.getByProductName(productName),"data listelendi");
    }

    @Override
    public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
        return new SuccessResult<Product>
                (this.productDao.getByProductNameAndCategory_CategoryId(productName,categoryId),"data listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
        return new SuccessResult<List<Product>>
                (this.productDao.getByProductNameOrCategory_CategoryId(productName,categoryId),"data listelendi");
    }

    @Override
    public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
        return new SuccessResult<List<Product>>
                (this.productDao.getByCategoryIn(categories),"data listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {
        return new SuccessResult<List<Product>>
                (this.productDao.getByProductNameContains(productName),"data listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
        return new SuccessResult<List<Product>>
                (this.productDao.getByProductNameStartsWith(productName),"data listelendi");
    }

    @Override
    public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
        return new SuccessResult<List<Product>>
                (this.productDao.getByNameAndCategory(productName,categoryId),"data listelendi");
    }

    @Override
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
        return new SuccessResult<List<ProductWithCategoryDto>>
                (this.productDao.getProductWithCategoryDetails(),"data listelendi");
    }
}
