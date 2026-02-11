variable "aws_region" {
  description = "AWS region for resources"
  type        = string
  default     = ""
}

variable "ami_id" {
  description = "AMI ID for EC2 instance"
  type        = string
  default     = ""
}

variable "instance_count" {
  description = "Number of EC2 instances to create"
  type        = number
  default     = 1
}