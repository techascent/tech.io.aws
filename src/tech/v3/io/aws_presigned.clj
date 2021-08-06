(ns tech.v3.io.aws-presigned
  ;;signers taken directly from sample
  (:import [tech.v3.io
            AWS4SignerForAuthorizationHeader
            AWS4SignerForChunkedUpload
            AWS4SignerForQueryParameterAuth
            AWS4SignerBase]
           [java.net URL]
           [java.util Map HashMap]))

(set! *warn-on-reflection* true)


(defn s3-get-auth
  "Create a presigned auth header good for a particular number of seconds.
  Intended to be as the value of an \"Authorization\" header.

  Example:

```clojure
tech.v3.io.aws-presigned> (s3-get-auth \"us-west-2\" \"innovb.data/investors.transit-json\" 3600
                                         (config/get-config :aws-access-key-id)
                                         (config/get-config :aws-secret-access-key))
\"X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIA3VHQDFSS6LRD2NEI/20210806/us-west-2/s3/aws4_request&X-Amz-Date=20210806T181354Z&X-Amz-Expires=null&X-Amz-SignedHeaders=host;x-amz-content-sha256&X-Amz-Signature=e162f152649e382d243df085f9772834d7eef9e9f94b0ad19fa0625806f400d3\"
```"
  ^String [region-name s3-bucket-path expire-seconds access-key secret-key]
  (let [url (URL. (str "https://s3-" region-name ".amazonaws.com/" s3-bucket-path))
        ^Map header-map {"x-amz-content-sha256" AWS4SignerBase/EMPTY_BODY_SHA256}]
    (-> (AWS4SignerForQueryParameterAuth. url "GET" "s3" region-name)
        (.computeSignature (HashMap. header-map)
                           (HashMap.)
                           AWS4SignerBase/EMPTY_BODY_SHA256
                           access-key secret-key))))


(defn s3-get-url
  "Create presigned url good for a particular number of seconds.

  Example:

```clojure
tech.v3.io.aws-presigned> (s3-get-url \"us-west-2\" \"innovb.data/investors.transit-json\" 3600
                                         (config/get-config :aws-access-key-id)
                                         (config/get-config :aws-secret-access-key))
\"https://s3-us-west-2.amazonaws.com/innovb.data/investors.transit-json?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIA3VHQDFSS6LRD2NEI/20210806/us-west-2/s3/aws4_request&X-Amz-Date=20210806T180728Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=e41128842cc5a3ffc4bf2e7eef19144e3c6881f03d149c7a15d091f61b182551\"
```"
  ^String [region-name s3-bucket-path expire-seconds access-key secret-key]
  (let [url (URL. (str "https://s3-" region-name ".amazonaws.com/" s3-bucket-path))
        ^Map header-map {}
        ^Map query-map {"X-Amz-Expires", (str expire-seconds)}]
    (str url "?" (-> (AWS4SignerForQueryParameterAuth. url "GET" "s3" region-name)
                     (.computeSignature (HashMap. header-map)
                                        (HashMap. query-map)
                                        AWS4SignerBase/UNSIGNED_PAYLOAD
                                        access-key secret-key)))))


(comment
  (require '[tech.config.core :as config])
  (s3-get-auth "us-west-2" "innovb.data/investors.transit-json" 3600
                 (config/get-config :aws-access-key-id)
                 (config/get-config :aws-secret-access-key))
  (s3-get-url "us-west-2" "innovb.data/investors.transit-json" 3600
                 (config/get-config :aws-access-key-id)
                 (config/get-config :aws-secret-access-key))
  )
