(ns tech.io.s3-test
  (:require [tech.io.s3]
            [tech.io :as io]
            [tech.io.temp-file :as temp-file]
            [tech.io.providers :as providers]
            [clojure.test :refer :all])
  (:import [java.io File]))




;;These tests only work if you have vault access credentials


(deftest s3-ls
  (temp-file/with-temp-dir
    temp-dir
    (io/with-provider (->> [(providers/vault-auth-provider nil {})
                            (providers/caching-provider temp-dir {})]
                           providers/provider-seq->wrapped-providers)
      (let [result (io/ls "s3://techascent.test/")
            first-file (->> result
                            (remove :directory?)
                            first
                            :url
                            io/get-object)]
        ;;Caching should always return a file.
        (is (instance? File first-file))
        (is (> (count result) 0))))))
