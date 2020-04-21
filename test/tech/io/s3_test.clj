(ns tech.io.s3-test
  (:require [tech.io.s3 :as s3]
            [tech.io :as io]
            [tech.io.auth :as io-auth]
            [tech.io.temp-file :as temp-file]
            [tech.config.core :as config]
            [clojure.test :refer :all])
  (:import [java.io File]))




;;These tests only work if you have vault access credentials

(deftest s3-ls
  (with-bindings {#'s3/*default-s3-provider*
                        (io-auth/authenticated-provider
                         (s3/s3-provider
                          {:tech.aws/endpoint (config/get-config :tech-aws-endpoint)})
                         (io-auth/vault-aws-auth-provider (tech.config.core/get-config
                                                           :tech-vault-aws-path)
                                                          {:re-request-time-ms 4000}))}
    (let [result (io/ls "s3://techascent.test/")
          first-file (->> result
                          (remove :directory?)
                          first
                              :url
                              io/get-object)]
      ;;Caching should always return a file.
      (is (not (nil? first-file)))
      (is (> (count result) 0)))))
