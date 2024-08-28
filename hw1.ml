let rec pow x n = 
    if n = 0 
    then 1
    else x * pow x (n - 1);;

let rec float_pow x n =
    if n = 0 
    then 1.
    else x *. float_pow x (n - 1);;

let rec compress lst = match lst with
    | [] -> []
    | a :: [] -> [a] 
    | a :: b :: t -> if a = b then compress (b :: t) else a :: compress (b :: t);;

let rec remove_if lst predicate = match lst with
    | [] -> [] 
    | a :: t -> if predicate a then (remove_if t predicate) else a :: remove_if t predicate;;

let rec slice lst x y = match lst with
    | [] -> []
    | a :: t -> if (x = 0) 
                then (if (y > 0) then a :: slice t 0 (y - 1) else slice t (x - 1) (y - 1)) 
                else slice t (x - 1) (y - 1);;

let rec equivs fn lst = 
    let rec create_class funct remaining = match remaining with
        | [] -> []
        | a :: t -> 
            if (funct a) 
            then a :: (create_class funct t)
            else (create_class funct t) in
                let rec remove_used lst1 lst2 = match lst1 with
                    | [] -> lst2
                    | b :: tl -> remove_used tl (remove_if lst2 ((=)b)) in
                        match lst with
                            | [] -> [[]]
                            | c :: rest -> let x = (create_class (fn c) lst) in 
                                match (remove_used x rest) with 
                                    | [] -> [x]
                                    | head :: tail -> x :: (equivs fn (remove_used x rest));;

let goldbachpair x = 
    let rec is_prime y z = 
        (z * z) > y || ((y mod z) <> 0 && is_prime y (z + 1)) in
            let rec find_pair a = 
                if is_prime a 2 && is_prime (x - a) 2 
                then (a, x - a) 
                else find_pair (a + 1) in
                    find_pair 2;;

let rec equiv_on f g lst = match lst with
    | [] -> true
    | a :: t -> if f a = g a then equiv_on f g t else false;;

let rec pairwisefilter cmp lst = match lst with
    | [] -> []
    | a :: [] -> [a]
    | a :: b :: t -> (cmp a b) :: pairwisefilter cmp t

let rec polynomial lst = fun x -> match lst with 
    | [] -> 0
    | a :: t -> let z = int_of_float((float_of_int x) ** (float_of_int (snd a))) in 
        ((fst a) * z) + (polynomial t) x;;

let rec powerset lst = 
    let rec aux fn rest_of_list = match rest_of_list with 
        | [] -> []
        | a :: t -> (fn a) :: (aux fn t) in 
        match lst with
            | [] -> [[]]
            | b :: c -> (powerset c) @ aux (fun rest -> b :: rest) (powerset c);;