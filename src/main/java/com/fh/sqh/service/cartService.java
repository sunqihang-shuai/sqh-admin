package com.fh.sqh.service;


import java.util.List;

public interface cartService {
    Integer addGoodsCart(Integer goodsId, Integer count);

    List queryGoodsCart();

    void updateGoodsCartIsChecked(String checkedIds);

    void deleteGoodsCartAll(List<Integer> list);

    void deleteGoodsCartByGoodsId(Integer goodsId);
}
