package org.miser.rdm.project.product.service;

import org.miser.rdm.project.product.domain.Product;
import java.util.List;

/**
 * 产品 服务层
 *
 * @author Barry
 * @date 2018-09-20
 */
public interface IProductService {
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
     * 删除产品信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductByIds(String ids);

}
