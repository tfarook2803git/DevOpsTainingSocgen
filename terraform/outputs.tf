output "instance_id" {
  description = "ID of the EC2 instance"
  value       = aws_instance.example.*.id[0]
}

output "instance_id" {
  description = "ID of the EC2 instance"
  value       = aws_instance.example.*.id[1]
}



