/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxAncestorDiff(TreeNode root) {
        //node数量>=2
        return searchSubTree(root).get(2);
    }
    
    private List<Integer> searchSubTree(TreeNode root)
    {
        if (root == null)
        {
            return null;
        }
        List<Integer> l = searchSubTree(root.left), r = searchSubTree(root.right);
        if (l == null && r == null) //叶节点
        {
            return new ArrayList()
            {
                {
                    add(root.val);
                    add(root.val);
                    add(0);
                }  
            };
        }
        int min, max, maxDiff;
        if (l != null && r != null) //合并
        {
            min = Math.min(l.get(0), r.get(0));
            max = Math.max(l.get(1), r.get(1));
            maxDiff = Math.max(l.get(2), r.get(2));
            maxDiff = Math.max(maxDiff, Math.abs(root.val - min));
            maxDiff = Math.max(maxDiff, Math.abs(root.val - max));
        }
        //单独处理一个子树
        else if (l != null)
        {
            min = l.get(0);
            max = l.get(1);
            maxDiff = l.get(2);
            maxDiff = Math.max(maxDiff, Math.abs(root.val - min));
        maxDiff = Math.max(maxDiff, Math.abs(root.val - max));
        }
        else
        {
            min = r.get(0);
            max = r.get(1);
            maxDiff = r.get(2);
            maxDiff = Math.max(maxDiff, Math.abs(root.val - min));
        maxDiff = Math.max(maxDiff, Math.abs(root.val - max));
        }
        
        return new ArrayList()
        {
            {
                add(min);
                add(max);
                add(maxDiff);
            }  
        };
    }
}