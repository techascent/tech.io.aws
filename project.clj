(defproject techascent/tech.io.aws "0.1-SNAPSHOT"
  :description "aws bindings for io subsystem"
  :url "http://github.com/tech-ascent/tech.io.aws"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [techascent/tech.io "1.0"]
                 [amazonica "0.3.133"
                  :exclusions [com.fasterxml.jackson.dataformat/jackson-dataformat-cbor
                               com.fasterxml.jackson.core/jackson-databind
                               com.amazonaws/aws-java-sdk
                               com.amazonaws/amazon-kinesis-client
                               com.amazonaws/dynamodb-streams-kinesis-adapter]]
                 [commons-logging "1.2"]
                 ;; Only incude S3 for now as that is the only service this
                 ;; project is using
                 ;; see: https://github.com/mcohen01/amazonica#for-the-memory-constrained
                 [com.amazonaws/aws-java-sdk-core "1.11.391" :exclusions [commons-logging]]
                 ;;We get a later commons-logging from here
                 [com.amazonaws/aws-java-sdk-s3 "1.11.391"]
                 [com.fasterxml.jackson.dataformat/jackson-dataformat-cbor "2.9.0"]
                 [com.fasterxml.jackson.core/jackson-databind "2.9.0"]
                 ])
