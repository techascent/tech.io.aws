(defproject techascent/tech.io.aws "4.08-4-SNAPSHOT"
  :description "aws bindings for io subsystem"
  :url "http://github.com/tech-ascent/tech.io.aws"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [techascent/tech.io "4.16"]
                 [amazonica "0.3.161"
                  :exclusions [com.fasterxml.jackson.dataformat/jackson-dataformat-cbor
                               com.fasterxml.jackson.core/jackson-databind
                               com.amazonaws/aws-java-sdk
                               com.amazonaws/amazon-kinesis-client
                               com.amazonaws/dynamodb-streams-kinesis-adapter]]
                 [commons-logging "1.2"]
                 ;; Only incude S3 for now as that is the only service this
                 ;; project is using
                 ;; see: https://github.com/mcohen01/amazonica#for-the-memory-constrained
                 [com.amazonaws/aws-java-sdk-core "1.11.698"
                  :exclusions [commons-logging]]
                 ;;We get a later commons-logging from here
                 [com.amazonaws/aws-java-sdk-s3 "1.11.698"]
                 [com.fasterxml.jackson.dataformat/jackson-dataformat-cbor "2.10.2"]
                 [com.fasterxml.jackson.core/jackson-databind "2.10.2"]
                 [joda-time/joda-time "2.10.2"]]
  :java-source-paths ["java"]
  :profiles {:dev {:dependencies [[amperity/vault-clj "0.7.0"]]}
             :codox {:dependencies [[com.cnuernber/codox "1.000"]
                                    [codox-theme-rdash "0.1.2"]]
                     :codox {:project {:name "tech.io.aws"}
                             :metadata {:doc/format :markdown}
                             :themes [:rdash]
                             :source-paths ["src"]
                             :output-path "docs"
                             :doc-paths ["topics"]
                             :source-uri "https://github.com/techascent/tech.io.aws/blob/master/{filepath}#L{line}"
                             :namespaces [tech.v3.io.s3
                                          tech.v3.io.aws-presigned]}}}
  :aliases {"codox" ["with-profile" "codox,dev" "run" "-m" "codox.main"]})
