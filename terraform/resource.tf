# EC2 Instance Resource
resource "aws_instance" "web_server" {
  ami           = var.ami_id
  instance_type = "t2.micro"
  count         = var.instance_count

  tags = {
    Name = "Instance-${count.index + 1}"
  }
}


