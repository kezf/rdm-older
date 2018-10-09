package org.miser.rdm.project.product.mapper;

import org.miser.rdm.project.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品 数据层
 *
 * @author Barry
 * @date 2018-09-20
 */
@Repository
public interface ProductMapper {
    /**
     * 查询产品信息
     *
     * @param productId 产品ID
     * @return 产品信息
     */
    public Product selectProductById(Integer productId);

    /**
     * 查询产品列表
     *
     * @param product 产品信息
     * @return 产品集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增产品
     *
     * @param product 产品信息
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改产品
     *
     * @param product 产品信息
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 删除产品
     *
     * @param productId 产品ID
     * @return 结果
     */
    public int deleteProductById(Integer productId);

    /**
     * 批量删除产品
     *
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductByIds(String[] productIds);

}