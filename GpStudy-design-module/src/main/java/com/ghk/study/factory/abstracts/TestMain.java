package com.ghk.study.factory.abstracts;

/**
 * 可以更细致的选择产品工厂,适用于产品类目比较多的场景
 * 优化点:在产品工厂创建的时候 传递的参数<范型>应该更能体现产品类型的特点。目前是共同的参数
 */
public class TestMain {
    public static void main(String[] args) {
        BMWCarFactory bmwCarFactory = new BMWCarFactory();
        JiaoAbstractCarBaseFunction bmwsJiaoCar = bmwCarFactory.createSJiaoCar(BMWJiaoCar.class);
        bmwsJiaoCar.drive("BMW jiao");
        bmwsJiaoCar.mannedDrive();
        SuvAbstractCarBaseFunction bmwsuvCar = bmwCarFactory.createSuvCar(BMWSuvCar.class);
        bmwsuvCar.drive("BMW suv");
        bmwsuvCar.crossdrive();

        BenzCarFactory benzCarFactory = new BenzCarFactory();
        JiaoAbstractCarBaseFunction benzJiaoCar = benzCarFactory.createSJiaoCar(BenZJiaoCar.class);
        benzJiaoCar.drive("Benz jiao");
        benzJiaoCar.mannedDrive();
        SuvAbstractCarBaseFunction benzSuvCar = benzCarFactory.createSuvCar(BenZSuvCar.class);
        benzSuvCar.drive("Benz suv");
        benzSuvCar.crossdrive();
    }
}
