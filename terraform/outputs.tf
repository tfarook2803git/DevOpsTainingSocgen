output "instance_id_1" {
  description = "ID of the EC2 instance"
  value       = aws_instance.example.*.id[0]
}

output "instance_id_2" {
  description = "ID of the EC2 instance"
  value       = aws_instance.example.*.id[1]
}



