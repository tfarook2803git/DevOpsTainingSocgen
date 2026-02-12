resource "aws_ecr_repository" "my_ecr" {
  name = "my-ecr-repo"
  image_tag_mutability = "MUTABLE"
  region = var.aws_region

   image_scanning_configuration {
    scan_on_push = true
  }

}