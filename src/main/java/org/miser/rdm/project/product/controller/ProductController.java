package org.miser.rdm.project.product.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.miser.framework.aspectj.lang.annotation.Log;
import org.miser.framework.aspectj.lang.constant.BusinessType;
import org.miser.rdm.project.product.domain.Product;
import org.miser.rdm.project.product.service.IProductService;
import org.miser.framework.web.controller.BaseController;
import org.miser.framework.web.page.TableDataInfo;
import org.miser.framework.web.domain.AjaxResult;

/**
 * 产品 信息操作处理
 *
 * @author Barry
 * @date 2018-09-20
 */
@Controller
@RequestMapping("/project/product")
public class ProductController extends BaseController {
    private String prefix = "project/product";

    @Autowired
    private IProductService productService;

    @RequiresPermissions("project:product:view")
    @GetMapping()
    public String product() {
        return prefix + "/product";
    }

    /**
     * 查询产品列表
     */
    @RequiresPermissions("project:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Product product) {
        startPage();
        List<Product> list = productService.selectProductList(product);
        return getDataTable(list);
    }

    /**
     * 新增产品
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品
     */
    @RequiresPermissions("project:product:add")
    @Log(title = "产品", action = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Product product) {
        return toAjax(productService.insertProduct(product));
    }

    /**
     * 修改产品
     */
    @GetMapping("/edit/{productId}")
    public String edit(@PathVariable("productId") Integer productId, ModelMap mmap) {
        Product product =productService.selectProductById(productId);
        mmap.put("product", product);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品
     */
    @RequiresPermissions("project:product:edit")
    @Log(title = "产品", action = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Product product) {
        return toAjax(productService.updateProduct(product));
    }

    /**
     * 删除产品
     */
    @RequiresPermissions("project:product:remove")
    @Log(title = "产品", action = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(productService.deleteProductByIds(ids));
    }

}
