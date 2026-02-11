# Terraform Infrastructure

This directory contains Terraform configuration for provisioning AWS infrastructure, including an EC2 instance with VPC, subnets, security groups, and related networking components.

## Directory Structure

```
terraform/
├── providers.tf          # AWS provider configuration
├── variables.tf          # Variable definitions
├── data.tf              # Data sources (AMI, AZs, etc.)
├── resource.tf          # EC2 instance and networking resources
├── outputs.tf           # Output values
├── terraform.tfvars.example  # Example variables file
└── .gitignore
```

## Files Overview

### `providers.tf`
- Defines Terraform version requirements (>= 1.0)
- Configures AWS provider with region and default tags
- Specifies AWS provider version (~> 5.0)

### `variables.tf`
- `aws_region`: AWS region (default: us-east-1)
- `environment`: Environment name (default: development)

### `data.tf`
- **aws_ami.ubuntu**: Latest Ubuntu 22.04 AMI
- **aws_availability_zones.available**: Available AZs in the region
- **aws_caller_identity.current**: Current AWS account info
- **aws_region.current**: Current AWS region info

### `resource.tf`
- **aws_instance.web_server**: EC2 instance (t3.micro)
- **aws_vpc.main**: VPC with CIDR 10.0.0.0/16
- **aws_subnet.main**: Subnet with CIDR 10.0.1.0/24
- **aws_internet_gateway.main**: Internet gateway for public access
- **aws_route_table.main**: Route table with IGW route
- **aws_security_group.web**: Security group with ingress rules
- **aws_key_pair.deployer**: SSH key pair
- **aws_eip.web**: Elastic IP for the instance

### `outputs.tf`
Exposes:
- `instance_id`: EC2 instance ID
- `instance_public_ip`: Elastic IP address
- `instance_private_ip`: Private IP address
- `security_group_id`: Security group ID
- `vpc_id`: VPC ID
- `subnet_id`: Subnet ID
- `ami_id`: Ubuntu AMI ID used

## Prerequisites

1. **Terraform** >= 1.0
   ```bash
   terraform -version
   ```

2. **AWS CLI** configured with credentials
   ```bash
   aws configure
   ```

3. **SSH Key Pair**
   Generate a key if you don't have one:
   ```bash
   ssh-keygen -t rsa -b 4096 -f ~/.ssh/id_rsa
   ```

## Setup & Deployment

### 1. Initialize Terraform
```bash
cd terraform
terraform init
```

### 2. Create Variables File
```bash
cp terraform.tfvars.example terraform.tfvars
# Edit terraform.tfvars with your desired values
```

### 3. Validate Configuration
```bash
terraform validate
```

### 4. Check Plan
```bash
terraform plan -out=tfplan
```

### 5. Apply Configuration
```bash
terraform apply tfplan
```

### 6. View Outputs
```bash
terraform output
```

## Connectivity

After deployment, connect to your EC2 instance:

```bash
# Get the public IP
PUBLIC_IP=$(terraform output -raw instance_public_ip)

# SSH into the instance
ssh -i ~/.ssh/id_rsa ubuntu@$PUBLIC_IP
```

## Cleanup

Destroy all resources when no longer needed:

```bash
terraform destroy
```

## GitHub Actions Workflow

The `.github/workflows/terraform.yml` workflow:
- Runs on push/PR to terraform files
- Validates syntax with `terraform validate`
- Checks formatting with `terraform fmt`
- Generates and uploads plan artifact
- Comments plan results on PRs (requires proper setup)

### Adding AWS Credentials to GitHub

For CI/CD, add AWS credentials as GitHub Secrets:
1. Go to Repository Settings → Secrets and Variables → Actions
2. Add:
   - `AWS_ACCESS_KEY_ID`
   - `AWS_SECRET_ACCESS_KEY`

Then update the workflow to use these credentials.

## Security Notes

⚠️ **Important:**
- Never commit `.tfvars` file with real values to version control
- Use `.gitignore` to exclude sensitive files:
  ```
  terraform.tfvars
  .terraform/
  *.tfstate
  *.tfstate.*
  .terraform.lock.hcl
  tfplan
  ```
- Store state files securely (consider Terraform Cloud or S3 backend)
- Use SSH key with appropriate permissions (400)
- Restrict security group ingress rules as needed

## Customization

### Change Instance Type
Edit `resource.tf` and update `instance_type`:
```hcl
instance_type = "t3.small"  # or t3.medium, t3.large, etc.
```

### Modify VPC/Subnet CIDR
Edit `resource.tf`:
```hcl
resource "aws_vpc" "main" {
  cidr_block = "10.1.0.0/16"
}
```

### Add More Security Group Rules
Add to `resource.tf`:
```hcl
resource "aws_security_group_rule" "allow_custom" {
  type              = "ingress"
  from_port         = 8080
  to_port           = 8080
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.web.id
}
```

## Useful Commands

```bash
# Format code
terraform fmt -recursive

# Validate syntax
terraform validate

# Show current state
terraform show

# Show specific resource
terraform state show aws_instance.web_server

# Refresh state
terraform refresh

# Get detailed plan in JSON
terraform plan -json > plan.json

# Destroy specific resource
terraform destroy -target=aws_instance.web_server
```

## Troubleshooting

### "InvalidKeyPair.NotFound"
Ensure your SSH public key exists at `~/.ssh/id_rsa.pub`

### "Error: resource does not exist" after apply
Run `terraform refresh` to update your state file

### "UnauthorizedOperation" AWS error
Check AWS credentials and IAM permissions

## Documentation

- [Terraform AWS Provider](https://registry.terraform.io/providers/hashicorp/aws/latest/docs)
- [AWS VPC Documentation](https://docs.aws.amazon.com/vpc/)
- [EC2 Documentation](https://docs.aws.amazon.com/ec2/)

## Support

For issues or questions, refer to:
- Terraform Docs: https://www.terraform.io/docs
- AWS Terraform Provider: https://registry.terraform.io/providers/hashicorp/aws/latest
