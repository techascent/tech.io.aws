(defproject techascent/tech.io.aws "4.08"
  :description "aws bindings for io subsystem"
  :url "http://github.com/tech-ascent/tech.io.aws"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-tools-deps "0.4.1"]
            [lein-eftest "0.5.3"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}
  :profiles {:dev {:dependencies [[amperity/vault-clj "0.7.0"]]}})
