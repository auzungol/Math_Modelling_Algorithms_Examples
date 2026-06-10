class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; this.next = null; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    // A. Bağlı listenin palindrom olup olmadığını kontrol eden iteratif algoritma
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true; // 4 work

        // 1. Adım: Bağlı listenin ortasını bul
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) { // n work
            slow = slow.next; // 1 work
            fast = fast.next.next; // 1 work
        }

        // 2. Adım: Listenin ikinci yarısını tersine çevir
        ListNode prev = null, current = slow; // 2 work
        while (current != null) { // n/2 work
            ListNode nextNode = current.next; // 1 work
            current.next = prev; // 1 work
            prev = current; // 1 work
            current = nextNode; // 1 work
        }

        // 3. Adım: İlk yarı ile tersine çevrilmiş ikinci yarıyı karşılaştır
        ListNode first = head, second = prev; // 2 work
        while (second != null) { // Sadece ikinci yarıyı kontrol etmek yeterlidir // n/2 work
            if (first.val != second.val) { // 1 work
                return false; // 1 work
            }
            first = first.next; // 1 work
            second = second.next; // 1 work
        }
        return true; // 1 work
    }

    // B. Main metodu içindeki test durumları
    public static void main(String[] args) {
        // Test Durumu 1: Palindromik liste
        ListNode node4 = new ListNode(1);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println(new Solution().isPalindrome(node1)); // Çıktı: true

        // Test Durumu 2: Palindromik olmayan liste
        ListNode nodeB = new ListNode(2);
        ListNode nodeA = new ListNode(1, nodeB);
        System.out.println(new Solution().isPalindrome(nodeA)); // Çıktı: false
    }
}