package com.ghk.study.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ShareCardPool
 * @Package: com.ghk.study.flyweight
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/29 17:19
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class ShareCardPool {
    private static final  List<ShareCard> carPool = new ArrayList<>(10);
    static{
        for(int i = 0; i<10; i++){
            carPool.add( new ShareCard());
        }
    }

    public static synchronized ShareCard getCar(){
        if(carPool.size() > 0){
            for (ShareCard shareCard : carPool) {
                Integer usedStatus = shareCard.getUsedStatus();
                if (usedStatus == 1) {
                    shareCard.setUsedStatus(0);
                    return shareCard;
                }
            }
        }
        return null;
    }

    public static synchronized void agentInto(ShareCard shareCard){
        shareCard.setUsedStatus(0);
        carPool.add(shareCard);
    }
}