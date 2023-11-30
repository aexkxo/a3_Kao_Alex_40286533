(ns a3_Kao_Alex_40286533
  (:require [clojure.pprint :as clojure.pprint]
            [clojure.string :as str]
            [clojure.java.io :as io]))
  ; this is where you would also include/require the compress module


; Display the menu and ask the user for the option
(defn showMenu
  []
  (println "\n\n*** Compression Menu ***")
  (println "------------------\n")
  (println "1. Display list of files")
  (println "2. Display file contents")
  (println "3. Compress a file")
  (println "4. Uncompress a file")
  (println "5. Exit")
  (do
    (print "\nEnter an option? ")
    (flush)
    (read-line)))

; Get the list of files in the current folder
(def f (io/file (System/getProperty "user.dir")))
(def fs (file-seq f))

; Display all files in the current folder
(defn option1
  []
  ; print the list of files in a format that is easy to read
  (clojure.pprint/pprint (map #(.getName %) fs)))

; function to check if the file exists
(defn file-exists?
  [file_name]
  (.exists (io/file file_name)))

; Read and display the file contents (if the file exists). Java's File class can be used to 
; check for existence first. 
(defn option2
  []
  (print "\nPlease enter a file name => ")
  (flush)
  (let [file_name (read-line)]
    ; checks if the file exists
    (if (file-exists? file_name)
      (slurp "frequency.txt")
      (println "*** The file called" file_name "does not exist... ***"))
    ; slurp the file   
    ; read the file
    ))
    ;; (with-open [rdr (io/reader "/home/aexkxo/Git/a3_Kao_Alex_40286533/t1.txt")]
    ;;   (doseq [line (line-seq rdr)]
    ;;     (println line))))



; Compress the (valid) file provided by the user. You will replace the println expression with code 
; that calls your compression function
(defn option3
  [] ;parm(s) can be provided here, if needed
  (print "\nPlease enter a file name => ")
  (flush)
  (let [file_name (read-line)]
    (println "now compress" file_name "with with the functions(s) you provide in compress.clj")))


; Decompress the (valid) file provided by the user. You will replace the println expression with code 
; that calls your decompression function
(defn option4
  [] ;parm(s) can be provided here, if needed
  (print "\nPlease enter a file name => ")
  (flush)
  (let [file_name (read-line)]
    (println "now decompress" file_name "with with the functions(s) you provide in compress.clj")))


; If the menu selection is valid, call the relevant function to 
; process the selection
(defn processOption
  [option] ; other parm(s) can be provided here, if needed
  (if (= option "1")
    (option1)
    (if (= option "2")
      (option2)
      (if (= option "3")
        (option3)  ; other args(s) can be passed here, if needed
        (if (= option "4")
          (option4)   ; other args(s) can be passed here, if needed
          (println "Invalid Option, please try again"))))))


; Display the menu and get a menu item selection. Process the
; selection and then loop again to get the next menu selection
(defn menu
  [] ; parm(s) can be provided here, if needed
  (let [option (str/trim (showMenu))]
    (if (= option "5")
      (println "\nGood Bye\n")
      (do
        (processOption option)
        (recur)))))   ; other args(s) can be passed here, if needed


; function that slice the file into a list of words with a space as a delimiter
(defn slice
  [file_name]
  (str/split (slurp file_name) #"\s"))


; function that maps a list of words to a map with the index (incremented as the key and the word as the value
(defn mapping-freq
  [list_words]
  (zipmap (range) list_words))

(defn load_data
  []
  (println "loading data...")
  ; read the file
  ; insert data in the map
  (mapping-freq (slice "frequency.txt"))
  ; return the map
  )

; ------------------------------
; Run the program. You might want to prepare the data required for the mapping operations
; before you display the menu. You don't have to do this but it might make some things easier

;; (menu) ; other args(s) can be passed here, if needed


;; playground
(print (mapping-freq (slice "frequency.txt")))

; function that writes a map in a file called test.txt
(defn write-file
  []
  (spit "test.txt" (mapping-freq (slice "frequency.txt"))))

(write-file)

