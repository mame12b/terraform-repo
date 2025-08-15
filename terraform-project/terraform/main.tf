resource "aws_instance" "example" {
  ami           = "ami-0de716d6197524dd9"
  instance_type = "t3.micro"
}
