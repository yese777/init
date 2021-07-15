package com.example.init.a;

/**
 * 反转链表
 */
public class a {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        System.out.println("===========操作前" + listNode1);
        ListNode result = reverseList(listNode1);
        System.out.println("===========操作后" + result);
    }

    public static ListNode reverseList(ListNode cur) {
        if (cur == null) {
            //head为当前节点，如果当前节点为空的话，那就什么也不做，直接返回null；
            return null;
        }
        //当前节点是head，pre为当前节点的前一节点，next为当前节点的下一节点
        ListNode pre = null;
        ListNode next = null;

        //需要pre和next的目的是让当前节点从pre->cur->next1->next2变成pre<-cur next1->next2
        //即pre让节点可以反转所指方向，但反转之后如果不用next节点保存next1节点的话，此单链表就此断开了
        //所以需要用到pre和next两个节点
        //1->2->3->4->5
        //1<-2<-3 4->5
        while (cur != null) {
            System.out.println("当前节点翻转前:" + cur);
            //做循环，如果当前节点不为空的话，始终执行此循环，此循环的目的就是让当前节点从指向next到指向pre
            //如此就可以做到反转链表的效果
            //先用next保存head的下一个节点的信息，保证单链表不会因为失去head节点的原next节点而就此断裂
            next = cur.next;
            System.out.println("下一个节点:" + next);
            //保存完next，就可以让head从指向next变成指向pre了，代码如下
            cur.next = pre;
            System.out.println("当前节点翻转后:" + cur);

            //head指向pre后，就继续依次反转下一个节点
            //让pre，cur，next依次向后移动一个节点，继续下一次的指针反转
            pre = cur;
            cur = next;
            System.out.println("---------");
        }
        //如果head为null的时候，pre就为最后一个节点了，但是链表已经反转完毕，pre就是反转后链表的第一个节点
        //直接输出pre就是我们想要得到的反转后的链表
        return pre;
    }
}
