resource "aws_eks_cluster" "example" {
  name = "farook-eks-cluster"
  role_arn = aws_iam_role.cluster.arn
  version  = "1.34"

  vpc_config {
    subnet_ids = [
      "subnet-08c1fb2a582442df6",
      "subnet-07e0884a91540d7f1",
      "subnet-0946aebdaddb00f6f"
    ]
  }
    depends_on = [
    aws_iam_role_policy_attachment.cluster_AmazonEKSClusterPolicy,
  ]
}


# Worker Nodes
# -------------------------------
resource "aws_eks_node_group" "demo_nodes" {
  cluster_name    = aws_eks_cluster.example.name
  node_group_name = "demo-nodes"
  node_role_arn   = aws_iam_role.eks_node_role.arn
  subnet_ids = [
      "subnet-08c1fb2a582442df6",
      "subnet-07e0884a91540d7f1",
      "subnet-0946aebdaddb00f6f"
  ]

  instance_types = ["t3.small"]

  scaling_config {
    desired_size = 1
    max_size     = 2
    min_size     = 1
  }

  depends_on = [
    aws_iam_role_policy_attachment.node_policy_1,
    aws_iam_role_policy_attachment.node_policy_2,
    aws_iam_role_policy_attachment.node_policy_3
  ]
}


  