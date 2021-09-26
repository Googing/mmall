package com.southwind.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.mmall.entity.Product;
import com.southwind.mmall.entity.ProductCategory;
import com.southwind.mmall.mapper.ProductCategoryMapper;
import com.southwind.mmall.mapper.ProductMapper;
import com.southwind.mmall.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.mmall.service.ProductService;
import com.southwind.mmall.vo.ProductCategoryVO;
import com.southwind.mmall.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 建强
 * @since 2021-09-11
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductCategoryVO> getAllProductCategoryVo() {
       /* //实体类转为vo
        List<ProductCategory> productCategoryList = productCategoryMapper.selectList(null);
        //转为vo
        ArrayList<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            BeanUtils.copyProperties(productCategory,productCategoryVO);
            productCategoryVOList.add(productCategoryVO);
        }*/
        //一级分类
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type",1);
        List<ProductCategory> levelOne = productCategoryMapper.selectList(wrapper);
        /*//第一种方式生成vo集合
        List<ProductCategoryVO> levelOneVo = new ArrayList<>();
        for (ProductCategory productCategory : levelOne) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            BeanUtils.copyProperties(productCategory,productCategoryVO);
            levelOneVo.add(productCategoryVO);
        }*/

        //第二种方式生成vo集合
        List<ProductCategoryVO> levelOneVo =levelOne.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
        //图片赋值
        //商铺信息赋值
        for (int i = 0; i < levelOneVo.size(); i++) {
            levelOneVo.get(i).setBannerImg("/images/banner"+i+".png");
            levelOneVo.get(i).setTopImg("/images/top"+i+".png");
            wrapper = new QueryWrapper();
            wrapper.eq("categorylevelone_id",levelOneVo.get(i).getId());
            List<Product> productList =   productMapper.selectList(wrapper);
            List<ProductVO> productVOList = productList.stream().map(e -> new ProductVO(e.getId(),e.getName(),e.getPrice(),e.getFileName())).collect(Collectors.toList());
            levelOneVo.get(i).setProductVOList(productVOList);
        }

        for (ProductCategoryVO  levelProductCategoryVO : levelOneVo) {
            wrapper = new QueryWrapper();
            wrapper.eq("type",2);
            wrapper.eq("parent_id",levelProductCategoryVO.getId());
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(wrapper);
            List<ProductCategoryVO> levelTwoVO = levelTwo.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
            levelProductCategoryVO.setChildren(levelTwoVO);
            for (ProductCategoryVO levelTwoProductCategoryVO : levelTwoVO) {
                wrapper = new QueryWrapper();
                wrapper.eq("type",3);
                wrapper.eq("parent_id",levelTwoProductCategoryVO.getId());
                List<ProductCategory> levelThree = productCategoryMapper.selectList(wrapper);
                List<ProductCategoryVO> levelThreeVO = levelThree.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
                levelTwoProductCategoryVO.setChildren(levelThreeVO);
            }
        }

        //二级分类
        //三级分类
        return levelOneVo;
    }
}
