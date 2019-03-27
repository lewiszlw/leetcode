//
// Created by lewis on 2019/3/27.
//
// No.136 SingleNumber
// 解决方法：异或。两个相同的数异或为0，0与任何数异或为该数不变，异或满足交换律、结合律。
//

#include <stdio.h>

int singleNumber(int* nums, int numsSize) {
    int res = 0;
    for(int i  = 0; i < numsSize; ++ i) {
        res = res ^ nums[i];
    }
    return res;
}

int main() {
    int nums[3] = {1, 2, 2};
    printf("%d \n", singleNumber(nums, 3));
    return 0;
}