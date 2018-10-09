package org.miser.rdm.project.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.miser.rdm.project.product.mapper.ProductMapper;
import org.miser.rdm.project.product.domain.Product;
import org.miser.rdm.project.product.service.IProductService;
import org.miser.common.support.Convert;

/**
 * 产品 服务层实现
 *
 * @author Barry
 * @date 2018-09-20
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询产品信息
     *
     * @param productId 产品ID
     * @return 产品信息
     */
    @Override
    public Product selectProductById(Integer productId) {
        return productMapper.selectProductById(productId);
    }

    /**
     * 查询产品列表
     *
     * @param product 产品信息
     * @return 产品集合
     */
    @Override
    public List<Product> selectProductList(Product product) {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增产品
     *
     * @param product 产品信息
     * @return 结果
     */
    @Override
    public int insertProduct(Product product) {
        return productMapper.insertProduct(product);
    }

    /**
     * 修改产品
     *
     * @param product 产品信息
     * @return 结果
     */
    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    /**
     * 删除产品对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductByIds(String ids) {
        return productMapper.deleteProductByIds(Convert.toStrArray(ids));
    }

}
