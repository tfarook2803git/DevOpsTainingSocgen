# EC2 Instance Resource
resource "aws_instance" "example" {
  ami           = var.ami_id
  instance_type = "t2.micro"
  count         = var.instance_count

  tags = {
    Name = "Farook-Instance-${count.index + 1}"
  }
}


