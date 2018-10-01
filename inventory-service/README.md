# Inventory Service

The inventory service is responsible for managing the inventory and product catalogs that are ordered on the online store.

https://neo4j.com/download-center/#releases

## How to install neo4j on CentOS 7.5

Neo4j Stable Yum Repo

First, you'll want our key:

$ cd /tmp
$ wget http://debian.neo4j.org/neotechnology.gpg.key
$ rpm --import neotechnology.gpg.key

Then, you'll want to add our yum repo to /etc/yum.repos.d/neo4j.repo:

$ cat <<EOF>  /etc/yum.repos.d/neo4j.repo
[neo4j]
name=Neo4j Yum Repo
baseurl=http://yum.neo4j.org/stable
enabled=1
gpgcheck=1
EOF

Finally, install a package

$ yum install -y neo4j

$ systemctl enable neo4j && systemctl restart neo4j && systemctl status neo4j

$ neo4j version
neo4j 3.4.7

$ curl -v -u neo4j:neo4j -X POST localhost:7474/user/neo4j/password -H "Content-type:application/json" -d "{\"password\":\"secret\"}"


In FireFox or Google Chrome open the following URL to login :
http://localhost:7474/browser



