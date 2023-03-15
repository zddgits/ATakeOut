package com.zd.takeout.common;

import java.util.Scanner;

class Commodity{
    int piece;//购买数量
    int price;//购买价格
}

public class MinPrice {
    private static int MAXCODE = 999;//商品编码的最大值
    private static int SALECOMB = 99;//优惠商品组合数
    private static int KIND = 5;     //商品种类
    private static int QUANTITY = 5; //购买某种商品数量的最大值

    private static int b;//购买商品种类数
    private static int s;//当前优惠组合数
    private static int[] num = new int[MAXCODE+1];//记录商品编码与商品种类的对应关系
    private static int[] product = new int[KIND+1];//记录不同种类商品的购买数量
    private static int[][] offer = new int[SALECOMB+1][KIND+1];//offer[i][j]: 商品组合的优惠价(j=0)；某种优惠组合中某种商品需要购买的数量(j>0)
    private static Commodity[] purch = new Commodity[KIND+1];//记录不同商品的购买数量和购买价格
    private static int[][][][][] cost = new int[QUANTITY+1][QUANTITY+1][QUANTITY+1][QUANTITY+1][QUANTITY+1];//记录本次购买的总花费

    private static void init(){
        Scanner input = new Scanner(System.in);

        int i,j,n,p,t,code;
        //置0初始化
        for(i=0; i<100; i++)
            for(j=0; j<6; j++)
                offer[i][j] = 0;

        for(i=0; i<6; i++){
            purch[i] = new Commodity();
            purch[i].piece = 0;
            purch[i].price = 0;
            product[i] = 0;
        }

        //从控制台录入数据
        b = input.nextInt();
        for(i=1; i<=b; i++){
            code = input.nextInt();
            purch[i].piece = input.nextInt();
            purch[i].price = input.nextInt();
            num[code] = i;//一个数组实现的哈希，目的是在O(1)的时间复杂度找到对用商品编码所在的数组下标
        }

        s = input.nextInt();
        for(i=1; i<=s; i++){
            t = input.nextInt();
            for(j=1; j<=t; j++){
                n = input.nextInt();
                p = input.nextInt();
                offer[i][num[n]] = p;
            }
            offer[i][0] = input.nextInt();
        }
    }


    /**
     *用于确定对于b种商品，给定每种商品的购买量，其最小花费
     *即确定子问题的最优解
     */
    private static void minicost(){
        //已经购买1~5类商品的数量
        int i,j,k,m,n;
        //找到对应优惠后的花费
        int p;
        //最小花费
        int minm = 0;
        //将最小花费初始化为没有优惠策略时的花费
        for(i=1; i<=b; i++)
            minm += product[i]*purch[i].price;

        //对s中优惠策略依次讨论
        for(p=1; p<=s; p++){
            i = product[1] - offer[p][1];//第一种商品扣除当前优惠组合后的剩余购买量（offer[i][j]: 商品组合的优惠价(j=0)；某种优惠组合中某种商品需要购买的数量(j>0)）
            j = product[2] - offer[p][2];
            k = product[3] - offer[p][3];
            m = product[4] - offer[p][4];
            n = product[5] - offer[p][5];
            //判断在当前优惠组合下，购买的商品总花费是否比没有优惠政策的花费少
            if(i>=0 && j>=0 && k>=0 && m>=0 && n>=0 && cost[i][j][k][m][n]+offer[p][0] < minm)
                //购买优惠组合前的花费+当前优惠组合花费
                minm = cost[i][j][k][m][n] + offer[p][0];
        }
        //子问题最优组合花费
        cost[product[1]][product[2]][product[3]][product[4]][product[5]] = minm;
    }



    /**
     *对于第i类商品,如果i大于最大商品种类b时，计算此时组合的最小花费
     */
    private static void comp(int i){//i类商品
        //确定一个子问题，计算一次当前最小花费
        if(i > b){
            minicost();
            return;
        }

        for(int j=0; j<=purch[i].piece; j++){//purch[i].piece表示第i类商品的最大数量
            product[i] = j;//记录第i类商品购买数量j的情况
            comp(i+1);//控制遍历所有的商品类
        }
    }

    /**
     * 输出结果
     */
    private static void out(){
        System.out.println(cost[product[1]][product[2]][product[3]][product[4]][product[5]]);
    }

    public static void main(String[] args){
        init();
        comp(1);//从第一类商品开始
        out();
    }
}
