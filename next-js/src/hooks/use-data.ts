import useSWR from 'swr';

const fetcher = (input: RequestInfo | URL, init?: RequestInit) => fetch(input, init).then(res => res.json())

export function useDataObject<T>(collection: string, id: string) {
    const { data: item, error, isLoading } = useSWR<T>(`/api/${collection}/${id}`, fetcher);
    return {
        item,
        error,
        isLoading
    }
}

export function useDataObjects<T>(collection: string, ids?: string[]) {
    if(ids) {
        throw new Error("Not implemented");
    }
    const { data: items, error, isLoading } = useSWR<T[]>(`/api/${collection}`, fetcher);
    return {
        items,
        error,
        isLoading
    }
}