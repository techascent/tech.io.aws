# tech.io.aws

AWS bindings for the tech.io system.

## Releasing

```
[Edit project file to get rid of SNAPSHOT and set to VERSION]
lein test
lein with-profile -dev deploy clojars
git commit -am $VERSION && git tag -a $VERSION -m $VERSION
[Edit project file to get rid of SNAPSHOT and set to (inc VERSION)-SNAPSHOT]
git commit -am (inc VERSION)-SNAPSHOT
git push
git push origin $VERSION
```

## License

Copyright © 2018 Tech Ascent, LLC

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
