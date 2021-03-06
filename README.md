# OSIAM Server

OSIAM is a secure identity management backend providing REST based services for
authentication and authorization. We achieve this by implementing two important
open standards:

* [OAuth 2.0](http://oauth.net/2/)
* [SCIM v2](http://www.simplecloud.info/) (still in draft phase)

OSIAM is published under the MIT licence, giving you the maximum freedom to
utilize OSIAM within you project or product.

For technical information and documentation visit the [wiki](https://github.com/osiam/server/wiki).
For additional information visit our official website at [www.osiam.org](https://www.osiam.org).

The current version can be found at http://maven-repo.evolvis.org/releases/org/osiam/osiam-server-parent/.

# Project Structure

OSIAM is built with Apache Maven. The module structure is:

* resource-server -- contains the source for the OSIAM Resource Server -> [Wiki](https://github.com/osiam/server/wiki)
* auth-server -- contains the source for the OSIAM Auth Server -> [Wiki](https://github.com/osiam/server/wiki)
* distribution -- creates and deploys the distribution archives

# Requirements

* Java 1.7
* PostgreSQL 9.2
* Tomcat 7.0
* At least 2GB RAM

# Issue Tracker for the Server

Issues, bugs, and change and feature requests can be brought to us at [the issue tracker](https://github.com/osiam/server/issues).
